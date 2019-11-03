using UnityEngine;

public class Player : MovingObject
{
    private float _direction;
    private bool isLookingRight = true;
    private bool isOnTheGround = true;
    private float walkingSpeed = 13f;
    private Rigidbody2D _rigidbody2D;
    private BoxCollider2D _boxCollider2D;

    void Start()
    {
        _rigidbody2D = GetComponent<Rigidbody2D>();
        _boxCollider2D = GetComponent<BoxCollider2D>();
    }

    // Update is called once per frame
    void Update()
    {
        Walk();
        if (Input.GetKeyDown(KeyCode.Space))
            Jump();
        CheckForSpriteFlip();
    }

    private void CheckForSpriteFlip()
    {
        if(_direction < 0 && isLookingRight) 
            Flip();
        else if(_direction > 0 && !isLookingRight) 
            Flip();
    }

    void Walk()
    {
        _direction = Input.GetAxis("Horizontal");
        var position = _rigidbody2D.transform.position;
        _rigidbody2D.MovePosition(new Vector2(
            position.x + walkingSpeed * Time.deltaTime * _direction,
            position.y));
    }

    void Jump()
    {
        _rigidbody2D.AddForce(new Vector2(0, 4000));
        isOnTheGround = false;
    }

    void Flip()
    {
        isLookingRight = !isLookingRight;
        Vector3 theScale = transform.localScale;
        theScale.x *= -1;
        transform.localScale = theScale;
    }
}