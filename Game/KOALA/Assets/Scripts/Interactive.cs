using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class Interactive : MonoBehaviour
{
    protected bool ObjectInArea => InteractedObjects.Any();
    protected readonly Dictionary<string, Collider2D> InteractedObjects;
    protected readonly HashSet<string> TriggersTags;

    public Interactive()
    {
        TriggersTags = new HashSet<string>(new[]
        {
            "Player"
        });
        InteractedObjects = new Dictionary<string, Collider2D>();
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            InteractedObjects[other.tag] = other;
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            InteractedObjects.Remove(other.tag);
    }
}
