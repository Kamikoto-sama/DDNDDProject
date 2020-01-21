using System;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

namespace Light
{
    public class MultiLightController : Interactive
    {
        public GameObject[] LightSources;
        private IEnumerable<LightController> lightControllers;
        private bool powerState = true;

        private void Start()
        {
            PrepareLightSources();
        }

        private void PrepareLightSources()
        {
            lightControllers = LightSources.Select(ls =>
            {
                var lightController = ls.GetComponent<LightController>();
                if (lightController == null)
                    throw new ArgumentException("At least one light object hasn't a LightController");
                return lightController;
            });
        }

        private void Update()
        {
            if (Input.GetButtonDown("Interact") && playerInArea)
                SwitchPower();
        }

        private void SwitchPower()
        {
            powerState = !powerState;
            foreach (var lightController in lightControllers)
                lightController.HasPower = powerState;
        }
    }
}
