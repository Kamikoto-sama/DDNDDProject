using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Boomerang : MonoBehaviour
{
    public GameObject boomerangPrefab;
    public GameObject boomerang;
    [Range(0, 71)] public float force;
    public Rigidbody2D boomerangRb;
    public static bool IsForcing;
    public static bool HaveBoom = true;
    public static bool Throwing = true;
    public static bool BoomIsComingBack;
    public Animator animator;
    public float comingBackVelocity;


    private void Start()
    {
        animator = GetComponent<Animator>();
    }

    void FixedUpdate()
    {
        if (Input.GetButton("ThrowBoomerang"))
        {
            if (HaveBoom)
            {
                IsForcing = true;
                animator.Play("throw1");
                if (force <= 70)
                {
                    force += 1.5f;
                }
            }
            else if (!BoomIsComingBack)
            {
                var dir = transform.position - boomerang.transform.position;
                if (dir.magnitude > 2.5)
                {
                    BoomIsComingBack = true;
                    boomerangRb.bodyType = RigidbodyType2D.Dynamic;
                    comingBackVelocity = dir.magnitude * 2;
                    boomerangRb.velocity = Vector2.zero;
                    boomerangRb.gravityScale = 0;
                }
                else
                {
                    TakeBoomerang();
                    //CanThrow = false;
                }
            }
        }

        //if (Input.GetButtonUp("ThrowBoomerang"))
        //    CanThrow = true;
        else if (IsForcing)
        {
            if (HaveBoom)
                Throw();
            IsForcing = false;
        }

        if (!HaveBoom)
        {
            var distance = (boomerang.transform.position - transform.position).magnitude;
            if (distance > 2.5 || BoomIsComingBack)
                Throwing = false;
        }

        if (BoomIsComingBack)
        {
            var dir = transform.position - boomerang.transform.position;
            boomerangRb.velocity = dir.normalized * (comingBackVelocity + 15);
            boomerangRb.angularVelocity = comingBackVelocity * 50 + 70;
        }
    }

    void Throw()
    {
        animator.Play("throw2");
        HaveBoom = false;
        var direction = GetDirectionToMause();
        var spawnPosition = transform.position;
        boomerang = Instantiate(boomerangPrefab, spawnPosition, Quaternion.identity);
        boomerangRb = boomerang.GetComponent<Rigidbody2D>();
        boomerangRb.AddForce(direction * force * 40);
        boomerangRb.angularVelocity = 36 * force;
        force = 0;
    }

    private Vector2 GetDirectionToMause()
    {
        var result = (Vector2)(Camera.main.ScreenToWorldPoint(Input.mousePosition) - transform.position);
        result /= result.magnitude;
        return result;
    }
    

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("Boomerang") && !Throwing)
        {
            TakeBoomerang();
        }
    }

    private void TakeBoomerang()
    {
        Destroy(boomerang);
        HaveBoom = true;
        BoomIsComingBack = false;
        Throwing = true;
    }
}
