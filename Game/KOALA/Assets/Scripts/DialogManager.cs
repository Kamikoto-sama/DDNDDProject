using System;
using UnityEngine;

public class DialogManager : MonoBehaviour
{
    public int ReplicaIndex;
    public DialogueNode[] Replicas;
    private bool _dialogStarted;
    private bool _playerInArea;
    public DialogBox DialogBox;

    private void OnTriggerEnter2D(Collider2D other) => _playerInArea = true;

    private void OnTriggerExit2D(Collider2D other) => _playerInArea = false;

    private void Update()
    {
        if (!Input.GetKeyDown(KeyCode.F) || !_playerInArea || _dialogStarted) return;
        _dialogStarted = true;
        OnGUI();
    }

    void OnGUI()
    {
        if (!_dialogStarted) return;
        GUI.Box (GetDialogBoxRect(), "");
        GUI.Label (GetDialogLabelRect(), Replicas[ReplicaIndex].NpcText);
        for (var i = 0; i < Replicas[ReplicaIndex].PlayerAnswer.Length; i++) {
            if (!GUI.Button(GetButtonRect(i), Replicas[ReplicaIndex].PlayerAnswer[i].Text)) continue;
            if (Replicas[ReplicaIndex].PlayerAnswer[i].EndDialog) {
                _dialogStarted = false;
            }
            ReplicaIndex = Replicas[ReplicaIndex].PlayerAnswer[i].ToNode;
        }
    }

    private Rect GetButtonRect(int buttonIndex)
    {
        var xPos = Screen.width / 2 - DialogBox.ButtonWidth / 2 + DialogBox.NpcTextMargin - 100;
        var yPos = Screen.height / 2 - DialogBox.Height / 2 + DialogBox.NpcTextMargin + 
                   DialogBox.NpcTextHeight + DialogBox.ButtonSpacing;
        yPos += buttonIndex * DialogBox.ButtonHeight + DialogBox.ButtonSpacing;
        return new Rect(xPos, yPos, DialogBox.ButtonWidth, DialogBox.ButtonHeight);
    }
    
    private Rect GetDialogLabelRect()
    {
        var xPos = Screen.width / 2 - DialogBox.Width / 2 + DialogBox.NpcTextMargin - 100;
        var yPos = Screen.height / 2 - DialogBox.Height / 2 + DialogBox.NpcTextMargin;
        var labelWidth = DialogBox.Width - 2 * DialogBox.NpcTextMargin;
        return new Rect(xPos, yPos, labelWidth, DialogBox.NpcTextHeight);
    }

    private Rect GetDialogBoxRect()
    {
        var xPos = Screen.width / 2 - DialogBox.Width / 2 - 100;
        var yPos = Screen.height / 2 - DialogBox.Height / 2;
        return new Rect(xPos, yPos, DialogBox.Width, DialogBox.Height);
    }
}
 
[Serializable]
public class DialogueNode
{
    public string NodeName;
    public string NpcText;
    public Answer[] PlayerAnswer;
}
 
 
[Serializable]
public class Answer
{
    public string Text;
    public int ToNode;
    public bool EndDialog;
}

[Serializable]
public class DialogBox
{
    public int Width = 400;
    public int Height = 250;
    public int NpcTextHeight = 90;
    public int NpcTextMargin = 5;
    public int ButtonHeight = 25;
    public int ButtonWidth = 250;
    public int ButtonSpacing = 2;
}