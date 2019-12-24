using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    public CharacterController2D controller;

    public float runSpeed;
    public float climbSpeed;
    float horizontalMove;
    float verticalMove;
    public Transform CheckCeiling;
    public bool jump;
    public bool crouch;
    public bool run;
    public bool isCrossingCeiling;
    public bool isStuck;
    public LayerMask whatIsLadder;
    public LayerMask whatIsGround;
    public float distance;
    public Animator animator;

    void Update()
    {
        horizontalMove = Input.GetAxisRaw("Horizontal") * runSpeed;
        animator.SetFloat("Speeds", Mathf.Abs(horizontalMove));
        isCrossingCeiling = Physics2D.OverlapCircle(CheckCeiling.position, .1f, whatIsGround);
        CheckForJump();
        CheckForCrouch();
        CheckForLadder();
        CheckForRun();
    }

    void FixedUpdate()
    {
        controller.Move(horizontalMove * Time.fixedDeltaTime, crouch, jump, run);
        jump = false;
    }

    private void CheckForJump()
    {
        if (Input.GetButtonDown("Jump"))
        {
            jump = true;
            print("space pressed");
        }
            
    }

    private void CheckForCrouch()
    {
        if (Input.GetButtonDown("Crouch") && !isStuck)
            crouch = true;
        if (Input.GetButtonUp("Crouch"))
            if (!isCrossingCeiling)
                crouch = false;
        if (crouch && isCrossingCeiling)
            isStuck = true;
        if (isStuck && !isCrossingCeiling)
        {
            crouch = false;
            isStuck = false;
        }
    }

    private void CheckForLadder()
    {
        RaycastHit2D hitInfo = Physics2D.Raycast(transform.position, Vector2.up, distance, whatIsLadder);
        if (hitInfo.collider == null) return;
        verticalMove = Input.GetAxisRaw("Vertical") * climbSpeed;
        controller.Climb(verticalMove, jump);
    }

    private void CheckForRun() =>
        run = Input.GetButton("Run") && !crouch;
}