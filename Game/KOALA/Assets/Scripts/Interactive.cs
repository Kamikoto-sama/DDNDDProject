using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class Interactive : MonoBehaviour
{
    protected bool ObjectInArea => InteractedTags.Any();
    protected readonly HashSet<string> InteractedTags;
    protected readonly HashSet<string> TriggersTags;

    public Interactive()
    {
        TriggersTags = new HashSet<string>(new[]
        {
            "Player"
        });
        InteractedTags = new HashSet<string>();
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            InteractedTags.Add(other.tag);
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        if (TriggersTags.Contains(other.tag))
            InteractedTags.Remove(other.tag);
    }
}
