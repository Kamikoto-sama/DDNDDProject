using UnityEngine;

public class DeathPoint : MonoBehaviour
{
    [SerializeField] private GameObject respawn;
    [SerializeField] private GameObject player;

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (!other.CompareTag("Player")) return;
        var position = respawn.transform.position;
        player.transform.position = new Vector2(position.x, position.y);
    }
}