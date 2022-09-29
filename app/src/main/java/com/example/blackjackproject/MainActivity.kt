package com.example.blackjackproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    lateinit var resultView : TextView
    lateinit var cl : ConstraintLayout
    var deck = mutableListOf<Card>()
//    val dealerCard1 = deck.random()
//    val dealerCard2 = deck.random()
//    val playerCard1 = deck.random()
//    val playerCard2 = deck.random()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.resultView)
        val playButton = findViewById<Button>(R.id.playButton)
        val hitButton = findViewById<Button>(R.id.hitButton)
        val stayButton = findViewById<Button>(R.id.stayButton)
        val playerView1 = findViewById<ImageView>(R.id.playerView1)
        val playerView2 = findViewById<ImageView>(R.id.playerView2)
        val playerView3 = findViewById<ImageView>(R.id.playerView3)
        val dealerView1 = findViewById<ImageView>(R.id.dealerView1)
        val dealerView2 = findViewById<ImageView>(R.id.dealerView2)
        val dealerView3 = findViewById<ImageView>(R.id.dealerView3)


        cl = findViewById(R.id.constraint_Layout)


        createDeck()
        deck.shuffle()
        backgroundColor()

        playButton.setOnClickListener {
            val dealerCard1 = deck[0]
            val dealerCard2 = deck[1]
            val playerCard1 = deck[2]
            val playerCard2 = deck[3]

            dealerView1.setImageResource(dealerCard1.image)
            dealerView2.setImageResource(dealerCard2.image)
            playerView1.setImageResource(playerCard1.image)
            playerView2.setImageResource(playerCard2.image)

        }

        hitButton.setOnClickListener {
            val playerCard3 = deck[4]

            playerView3.setImageResource(playerCard3.image)

        }
        stayButton.setOnClickListener {
            val dealerCard3 = deck[5]

            dealerView3.setImageResource(dealerCard3.image)
        }
//        if (playerSumHit() > 21) {
//            resultView.text = "BLACKJACK NOT"
//        }
//        when (21) {
//            dealerSumStay() -> resultView.text = "BLACKJACK"
//            dealerSumHit() -> resultView.text = "BLACKJACK"
//            playerSumHit() -> resultView.text = "BLACKJACK"
//            playerSumStay() -> resultView.text = "BLACKJACK"
//            dealerSumHit() -> Log.d("!!!", "BLACKJACK")
//        }

    }
    fun createDeck() {
        val card1 = Card(R.drawable.heart1, value = 11)
        val card2 = Card(R.drawable.heart2, value = 2)
        val card3 = Card(R.drawable.heart3, value = 3)
        val card4 = Card(R.drawable.heart4, value = 4)
        val card5 = Card(R.drawable.heart5, value = 5)
        val card6 = Card(R.drawable.heart6, value = 6)
        val card7 = Card(R.drawable.heart7, value = 7)
        val card8 = Card(R.drawable.heart8, value = 8)
        val card9 = Card(R.drawable.heart9, value = 9)
        val card10 = Card(R.drawable.heart10, value = 10)
        val cardking1 = Card(R.drawable.heartking, value = 10)
        val cardking2 = Card(R.drawable.heartking, value = 10)
        val cardking3 = Card(R.drawable.heartking, value = 10)

        deck.add(card1)
        deck.add(card2)
        deck.add(card3)
        deck.add(card4)
        deck.add(card5)
        deck.add(card6)
        deck.add(card7)
        deck.add(card8)
        deck.add(card9)
        deck.add(card10)
        deck.add(cardking1)
        deck.add(cardking2)
        deck.add(cardking3)

    }
    fun backgroundColor() {
        cl.setBackgroundResource(R.color.teal_200)
    }
    fun dealerSumHit(): Int {
        val dealerHand = deck[0].value + deck[1].value + deck[4].value
        return dealerHand
    }
    fun dealerSumStay(): Int {
        val dealerHandStay = deck[0].value + deck[1].value
        return dealerHandStay
    }
    fun playerSumStay(): Int {
        val playerHand = deck[2].value + deck[3].value
        return playerHand
    }
    fun playerSumHit(): Int {
        val playerHand = deck[2].value + deck[3].value + deck[5].value
        return playerHand
    }
}