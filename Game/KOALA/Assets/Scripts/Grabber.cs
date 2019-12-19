using UnityEngine;
using UnityEngine.Serialization;

public class Grabber : MonoBehaviour
{
    public bool grabbed;
    private RaycastHit2D hit;
    public float distance;
    public Transform holdPoint;

    // Start is called before the first frame update
    void Start()
    {
    }

    void Update()
    {
        if (Input.GetButtonDown("Grab"))
        {
            if (!grabbed)
            {
                Physics2D.queriesStartInColliders = false;
                hit = Physics2D.Raycast(transform.position, Vector3.right * transform.localScale.x, distance);
                if (hit.collider != null && hit.collider.CompareTag("Grabbable"))
                {
                    grabbed = true;
                }
            }
            else
            {
                grabbed = false;
            }
        }

        if (grabbed)
        {
            hit.collider.gameObject.transform.position = GameObject.FindGameObjectWithTag("Player").transform.position;
        }
            
    }

    private void OnDrawGizmos()
    {
        Gizmos.color = Color.green;
        var transform1 = transform;
        Gizmos.DrawLine(transform1.position, distance * transform1.localScale.x * Vector3.right);
    }
}