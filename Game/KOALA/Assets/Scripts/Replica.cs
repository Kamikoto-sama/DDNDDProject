using System;
using UnityEngine;

[Serializable]
public class Replica: MonoBehaviour
{
    [TextArea(3, 100)]
    public string text;
    public Replica[] answers;
    internal bool watched;
}