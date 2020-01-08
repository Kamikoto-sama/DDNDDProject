using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoomerangCollision : MonoBehaviour
{
    public Rigidbody2D rigidbody2D;

//    void Start()
//    {
//        rigidbody2D = GetComponent<Rigidbody2D>();
//    }

    void OnCollisionEnter2D(Collision2D other)
    {
        rigidbody2D = GetComponent<Rigidbody2D>();
        if (/*other.gameObject.CompareTag("Ground") && */!Boomerang.BoomIsComingBack)
            rigidbody2D.bodyType = RigidbodyType2D.Static;
    }
}
