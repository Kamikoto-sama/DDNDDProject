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
    private Rigidbody2D _rigidbody2D;
    private Vector3 _velocity = Vector3.zero;
    [Range(0, .3f)] [SerializeField] private float _movementSmoothing = .05f;

    public enum EntityState
    {
        GoA,
        GoB,
        Wait
    }
    
    private void Awake()
    {
        _rigidbody2D = GetComponent<Rigidbody2D>();
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
                _rigidbody2D.velocity = Vector2.zero;
                _rigidbody2D.sleepMode = RigidbodySleepMode2D.StartAsleep;

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
        Vector2 turgetVelocity = Vector2.zero;
        if (Math.Abs(transform.position.x - targetX) < 0.1)
        {
            state = EntityState.Wait;
            return;
        }
        if (transform.position.x > targetX)
        {

            isLookingRight = false;
            turgetVelocity = new Vector2(-speed, 0);
        }
        if (transform.position.x < targetX)
        {

            isLookingRight = true;
            turgetVelocity = new Vector2(speed, 0);
        }

        _rigidbody2D.velocity = Vector3.SmoothDamp(_rigidbody2D.velocity, turgetVelocity, ref _velocity, _movementSmoothing);
        //transform.position = Vector3.MoveTowards(transform.position, new Vector3(targetX, transform.position.y), speed * Time.deltaTime);
    }

    IEnumerator Stay(EntityState nextState)
    {
        yield return new WaitForSeconds(delay);
        state = nextState;
    }
}
