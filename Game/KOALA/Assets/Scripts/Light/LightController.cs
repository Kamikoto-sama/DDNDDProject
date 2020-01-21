using UnityEngine;
using UnityEngine.Experimental.Rendering.LWRP;

namespace Light
{
	public class LightController : Interactive
	{
		private Light2D lightComponent;
		public bool HasPower { get; set; } = true;
		private bool lightState = true;

		private void Start() => lightComponent = GetComponent<Light2D>();

		private void Update()
		{
			if (Input.GetButtonDown("Interact") && playerInArea)
				lightState = !lightState;
			lightComponent.enabled = HasPower && lightState;
		}
	}
}
