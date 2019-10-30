using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DialogManager : MonoBehaviour
{
    public Replica StartReplica;
    private bool _playerInArea;
    private bool _dialogStarted;
    public Text ReplicaText;

    private void Update()
    {
        if (!Input.GetKeyDown(KeyCode.F) || !_playerInArea || _dialogStarted) return;
        _dialogStarted = true;
        DisplayReplica(StartReplica);
    }

    private void DisplayReplica(Replica replica)
    {
        ReplicaText.text = replica.text;
    }

    private void OnTriggerEnter2D(Collider2D other) => _playerInArea = true;

    private void OnTriggerExit2D(Collider2D other) => _playerInArea = false;
}