using UnityEngine;
using UnityEngine.Experimental.Rendering.LWRP;

public class LightControl : MonoBehaviour
{
	private bool _playerInArea;
	private Light2D _lightComponent;

	private void Start() => _lightComponent = GetComponent<Light2D>();

	private void Update()
	{
		if (Input.GetKeyDown(KeyCode.F) && _playerInArea)
			_lightComponent.enabled = !_lightComponent.enabled;
	}

	private void OnTriggerEnter2D(Collider2D other)
	{
		if (other.CompareTag("Player"))
			_playerInArea = true;
	}

	private void OnTriggerExit2D(Collider2D other)
	{
		if (other.CompareTag("Player"))
			_playerInArea = false;
	}
}
