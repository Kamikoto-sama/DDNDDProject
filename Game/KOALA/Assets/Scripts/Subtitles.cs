using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Subtitles : MonoBehaviour
{
    public Text TextField;
    public Replica[] Replicas;

    public void Init() => StartCoroutine(PrintReplicas());

    private IEnumerator PrintReplicas()
    {
        foreach (var replica in Replicas)
        {
            TextField.text = replica.Text;
            yield return new WaitForSeconds(replica.Time);
        }
    }
}

[Serializable]
public class Replica
{
    public float Time = 2;
    [TextArea(1, 10)]
    public string Text;
}
