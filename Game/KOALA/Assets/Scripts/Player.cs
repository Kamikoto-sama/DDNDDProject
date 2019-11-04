using System;
using UnityEngine;
using UnityEngine.EventSystems;

public class Player : MovingObject
{
    private float speedX;
    private bool isLookingRight = true;
    private bool isGrounded;
    private bool isCrouching = false;
    public float walkingSpeed;
    public float runningSpeed;
    public float crouchingSpeed;
    public float verticalImpulse;
    private Rigidbody2D _rigidbody2D;
    private BoxCollider2D _boxCollider2D;

    void Start()
    {
        _rigidbody2D = GetComponent<Rigidbody2D>();
        _boxCollider2D = GetComponent<BoxCollider2D>();
    }

    private void FixedUpdate()
    {
        CheckForCrouch();
        Move();
        CheckForSpriteFlip();
    }

    private void Move()
    {
        MoveX();
        if (Input.GetKeyDown(KeyCode.Space) && isGrounded)
            Jump();
    }

    private void MoveX()
    {
        float movingSpeed =
            !isCrouching ? Input.GetKey(KeyCode.LeftShift) ? runningSpeed : walkingSpeed : crouchingSpeed;
        if (Input.GetKey(KeyCode.A))
            speedX = -movingSpeed;
        else if (Input.GetKey(KeyCode.D))
            speedX = movingSpeed;
        transform.Translate(speedX, 0, 0);
        speedX = 0;
    }

    private void CheckForCrouch()
    {
        if (Input.GetKeyDown(KeyCode.C))
            isCrouching = !isCrouching;
    }

    void Jump()
    {
        _rigidbody2D.AddForce(new Vector2(0, verticalImpulse), ForceMode2D.Impulse);
        isGrounded = false;
    }

    private void CheckForSpriteFlip()
    {
        var direction = Input.GetAxis("Horizontal");
        if (direction < 0 && isLookingRight)
            Flip();
        else if (direction > 0 && !isLookingRight)
            Flip();
    }

    void Flip()
    {
        isLookingRight = !isLookingRight;
        var transform1 = transform;
        Vector3 theScale = transform1.localScale;
        theScale.x *= -1;
        transform1.localScale = theScale;
    }

    private void OnCollisionEnter2D(Collision2D other)
    {
        if (other.gameObject.CompareTag("Ground"))
            isGrounded = true;
    }
}