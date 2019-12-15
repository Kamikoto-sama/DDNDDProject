using System.Collections.Generic;
using UnityEngine;

public class Interactive : MonoBehaviour
{
    protected bool playerInArea;
    protected readonly HashSet<string> triggersTags;

    public Interactive() =>
        triggersTags = new HashSet<string>(new[]
        {
            "Player"
        });

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (triggersTags.Contains(other.tag))
            playerInArea = true;
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        if (triggersTags.Contains(other.tag))
            playerInArea = false;
    }
}
