using System;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class Player : MovingObject
{
    public Sprite crouchingSprite;
    public Sprite usualSprite;
    private float speedX;
    private bool isLookingRight = true;
    private bool isGrounded;
    private bool isCrouching = false;
    public float walkingSpeed;
    public float runningSpeed;
    public float crouchingSpeed;
    public float verticalImpulse;
    private Rigidbody2D _rigidbody2D;
    private BoxCollider2D _usualboxCollider2D;
    private BoxCollider2D _crouchingboxCollider2D;

    void Start()
    {
        _rigidbody2D = GetComponent<Rigidbody2D>();
        _usualboxCollider2D = GetComponent<BoxCollider2D>();
        _crouchingboxCollider2D = GetComponent<BoxCollider2D>();
        _crouchingboxCollider2D.enabled = false;
    }

    private void FixedUpdate()
    {
        if (Input.GetKeyDown(KeyCode.C))
            CheckForCrouch();
        if (Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.D) || Input.GetKeyDown(KeyCode.Space))
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
        isCrouching = !isCrouching;
        SetCurrentSprite();
        SetCurrentBoxCollider2D();
    }

    private void SetCurrentBoxCollider2D()
    {
        _crouchingboxCollider2D.enabled = isCrouching;
        _usualboxCollider2D.enabled = !isCrouching;
    }

    private void SetCurrentSprite()
    {
        var currntSpriteRenderer = GetComponent<SpriteRenderer>();
        currntSpriteRenderer.sprite = isCrouching ? crouchingSprite : usualSprite;
        var size = currntSpriteRenderer.size;
        size = isCrouching ? new Vector2(size.x / 1.8f, size.y / 1.8f) : new Vector2(size.x * 1.8f, size.y * 1.8f);
        currntSpriteRenderer.size = size;
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