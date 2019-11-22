using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    public CharacterController2D controller;

    public float runSpeed;
    public float climbSpeed;
    float horizontalMove;
    float verticalMove;
    public bool jump = false;
    public bool crouch = false;
    public LayerMask whatIsLadder;
    public LayerMask whatIsGround;
    public float distance;

    void Update()
    {
        horizontalMove = Input.GetAxisRaw("Horizontal") * runSpeed;
        if (Input.GetButtonDown("Jump"))
            jump = true;
        if (Input.GetButtonDown("Crouch"))
            crouch = true;
        else if (Input.GetButtonUp("Crouch"))
            crouch = false;
        CheckForLadder();
        if (Input.GetButtonDown("Run"))
            runSpeed *= 2;
        else if (Input.GetButtonUp("Run"))
            runSpeed /= 2;
    }

    private void CheckForLadder()
    {
        RaycastHit2D hitInfo = Physics2D.Raycast(transform.position, Vector2.up, distance, whatIsLadder);
        if (hitInfo.collider == null) return;
        verticalMove = Input.GetAxisRaw("Vertical") * climbSpeed;
        controller.Climb(verticalMove, jump);
    }

    void FixedUpdate()
    {
        controller.Move(horizontalMove * Time.fixedDeltaTime, crouch, jump);
        jump = false;
    }
}