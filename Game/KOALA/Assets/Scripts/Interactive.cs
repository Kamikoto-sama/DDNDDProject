using System.Collections.Generic;
using UnityEngine;

public class Interactive : MonoBehaviour
{
    protected bool ObjectInArea;
    protected readonly HashSet<string> TriggersTags;

    public Interactive() =>
        TriggersTags = new HashSet<string>(new[]
        {
            "Player"
        });

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            ObjectInArea = true;
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            ObjectInArea = false;
    }
}
