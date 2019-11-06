using System;
using System.Collections;
using UnityEngine;

public class NPCMovingCP : MonoBehaviour
{
    public float aCoordX;
    public float bCoordX;
    public float speed;
    public float delay;
    public bool isMoving;
    public bool isLookingRight;
    public EntityState state;
    public Vector3 dir;
    private NPCVision _vision;

    public enum EntityState
    {
        GoA,
        GoB,
        Wait
    }

    void Start()
    {
        _vision = GetComponent<NPCVision>();
        state = EntityState.GoA;
        if (transform.position.x > aCoordX)
            dir = Vector3.left;
        else
        {
            dir = Vector3.right;
        }
    }
    
    void Update()
    {
        _vision.isReverse = !isLookingRight;
        switch (state)
        {
            case EntityState.Wait:
                EntityState nextState;
                if (Math.Abs(transform.position.x - aCoordX) < Math.Abs(transform.position.x - bCoordX))
                    nextState = EntityState.GoB;
                else
                    nextState = EntityState.GoA;
                if (isMoving)
                {
                    StartCoroutine(Stay(nextState));
                    isMoving = false;
                }

                break;
            case EntityState.GoA:
                isMoving = true;
                GoTo(aCoordX);
                break;
            case EntityState.GoB:
                isMoving = true;
                GoTo(bCoordX);
                break;
        }
            
    }

    void GoTo(float targetX)
    {
        if (transform.position.x > targetX)
        {
            isLookingRight = false;
        }
        if (transform.position.x < targetX)
        {
            isLookingRight = true;
        }
        if (Math.Abs(transform.position.x - targetX) < 0.001)
        {
            state = EntityState.Wait;
            return;
        }
        transform.position = Vector3.MoveTowards(transform.position, new Vector3(targetX, transform.position.y), speed * Time.deltaTime);
    }

    IEnumerator Stay(EntityState nextState)
    {
        yield return new WaitForSeconds(delay);
        state = nextState;
    }
}
