using System;
using UnityEngine;

public class NPCVision : MonoBehaviour
{
    public string targetTag = "Player";
    public int rays = 12;
    public int distance = 15;
    public float angle = 30;
    public Vector2 offset;
    public float rotation;
    public bool isReverse = false;
    private GameObject _target;

    void Start()
    {
        _target = GameObject.FindGameObjectWithTag(targetTag);
    }

    public void Reverse()
    {
        isReverse = !isReverse;
    }

    bool GetRaycast(Vector2 dir) 
    {
        var result = false;
        var pos = (Vector2)transform.position + offset;
        var hit = Physics2D.Raycast(pos, dir, distance);
        if (hit.collider != null)
        {
            if (hit.transform.gameObject == _target)
            {
                result = true;
                Debug.DrawLine((Vector3)pos, hit.point, Color.green);
            }
            else
            {
                Debug.DrawLine((Vector3)pos, hit.point, Color.blue);
            }
        }
        else
        {
            Debug.DrawRay(new Vector3(pos.x, pos.y), new Vector3(dir.x, dir.y) * distance, Color.red);
        }
        return result;
    }

    bool RayToScan()
    {
        bool result = false;
        bool isHit = false;
        float rayAngle = GetStartAngle();
        for (int i = 0; i < rays; i++)
        {
            var x = Mathf.Cos(rayAngle);
            var y = Mathf.Sin(rayAngle);
            
            if (isReverse)
                rayAngle -= angle * Mathf.Deg2Rad / (rays - 1);
            else
                rayAngle += angle * Mathf.Deg2Rad / (rays - 1);

                Vector2 dir = transform.TransformDirection(new Vector2(x, y));
            if (GetRaycast(dir)) isHit = true;
        }

        if (isHit) result = true;
        return result;
    }

    private float GetStartAngle()
    {
        var rayAngle = rotation * Mathf.Deg2Rad;
        rayAngle -= angle * Mathf.Deg2Rad / 2;
        if (isReverse)
            rayAngle = Mathf.PI - rayAngle;
        return rayAngle;
    }

    void Update()
    {
        if (Math.Abs(transform.position.x - _target.transform.position.x) < distance)
        {
            if (RayToScan())
            {
                print("Объект обнаружен!");
            }
            else
            {
                // Поиск цели...
            }
        }
    }
}
