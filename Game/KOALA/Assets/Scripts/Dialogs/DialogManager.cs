using System;
using UnityEngine;

namespace Dialogs
{
    public class DialogManager : Interactive
    {
        public DialogueNode[] Replicas;
        public DialogBox DialogBox;
    
        private int _currentReplica;
        private bool _dialogStarted;

        private void Update()
        {
            if (!Input.GetKeyDown(KeyCode.F) || !ObjectInArea || _dialogStarted) return;
            _dialogStarted = true;
            OnGUI();
        }

        void OnGUI()
        {
            if (!_dialogStarted) return;
            GUI.Box (GetDialogBoxRect(), "");
            GUI.Label (GetDialogLabelRect(), Replicas[_currentReplica].NpcText);
            for (var i = 0; i < Replicas[_currentReplica].PlayerAnswer.Length; i++) {
                if (!GUI.Button(GetButtonRect(i), Replicas[_currentReplica].PlayerAnswer[i].Text)) continue;
                if (Replicas[_currentReplica].PlayerAnswer[i].EndDialog) {
                    _dialogStarted = false;
                }
                _currentReplica = Replicas[_currentReplica].PlayerAnswer[i].ToNode;
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
        [TextArea(3, 100)]
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
}