using UnityEngine;
using System.Collections;

public class CameraFollow2D : MonoBehaviour
{

    public float damping = 1.5f;
    public Vector2 offset = new Vector2(2f, 1f);
    public bool faceLeft;
    private Transform player;
    private int lastX;

    void Start()
    {
        offset = new Vector2(Mathf.Abs(offset.x), offset.y);
        FindPlayer(faceLeft);
    }

    public void FindPlayer(bool playerFaceLeft)
    {
        player = GameObject.FindGameObjectWithTag("Player").transform;
        var position = player.position;
        lastX = Mathf.RoundToInt(position.x);
        var transform1 = transform;
        if (playerFaceLeft)
        {
            transform1.position = new Vector3(position.x - offset.x, position.y + offset.y,
                transform1.position.z);
        }
        else
        {
            transform1.position = new Vector3(position.x + offset.x, position.y + offset.y,
                transform1.position.z);
        }
    }

    void Update()
    {
        if (player)
        {
            int currentX = Mathf.RoundToInt(player.position.x);
            if (currentX > lastX) faceLeft = false;
            else if (currentX < lastX) faceLeft = true;
            lastX = Mathf.RoundToInt(player.position.x);

            Vector3 target;
            if (faceLeft)
            {
                var position = player.position;
                target = new Vector3(position.x - offset.x, position.y + offset.y, transform.position.z);
            }
            else
            {
                var position = player.position;
                target = new Vector3(position.x + offset.x, position.y + offset.y, transform.position.z);
            }

            Vector3 currentPosition = Vector3.Lerp(transform.position, target, damping * Time.deltaTime);
            transform.position = currentPosition;
        }
    }
}