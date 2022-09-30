package com.example.blackjackproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    lateinit var playerResultView : TextView
    lateinit var dealerResultView : TextView
    lateinit var cl : ConstraintLayout
    var deck = mutableListOf<Card>()
    var dealerHand = 0
    var playerHand = 0
    var playerScore = 0
    var dealerScore = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dealerResultView = findViewById(R.id.dealerResultView)
        playerResultView = findViewById(R.id.playerResultView)
        val hitButton = findViewById<Button>(R.id.hitButton)
        val stayButton = findViewById<Button>(R.id.stayButton)
        val playerView1 = findViewById<ImageView>(R.id.playerView1)
        val dealerView1 = findViewById<ImageView>(R.id.dealerView1)
        val dealerScoreView = findViewById<TextView>(R.id.dealerScoreText)
        val playerScoreView = findViewById<TextView>(R.id.playerScoreText)
        var score = intent.getIntExtra("dealerScore", 0)


        cl = findViewById(R.id.constraint_Layout)


        createDeck()
        deck.shuffle()
        backgroundColor()

        hitButton.setOnClickListener {
            val playerCard = deck.random()
            playerView1.setImageResource(playerCard.image)
            playerHand += playerCard.value
            playerResultView.text = "$playerHand"
            if (playerHand > 21) {
                playerResultView.text = "BUST"
                startActivity(intent)
            } else if (playerHand == 21) {
                playerResultView.text = "BLACKJACK!"

            }

        }
        stayButton.setOnClickListener{
            GlobalScope.launch(Dispatchers.Main) {
                while (dealerHand <= 16) {
                    val dealerCard = deck.random()
                    dealerView1.setImageResource(dealerCard.image)
                    dealerHand += dealerCard.value
                    dealerResultView.text = "$dealerHand"
                    delay(500)
                }
                if (dealerHand > 21) {
                    dealerResultView.text = "BUST"

                } else if (dealerHand == 21) {
                    dealerResultView.text = "$dealerHand BLACKJACK!"
                } else if (dealerHand > playerHand) {
                    dealerScore ++
                    dealerScoreView.text = "Dealer Score: $dealerScore"
                }
            }
        }
//        if (dealerHand > playerHand) {
//            playerResultView.text = "Dealer Won!"
//        }else if (dealerHand < playerHand){
//            dealerResultView.text = "Player Won!"
//        }else "DRAW"

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
    fun draw(){

    }
//    fun dealerSumHit(): Int {
//        val dealerHand = deck[0].value + deck[1].value + deck[4].value
//        return dealerHand
//    }
//    fun dealerSumStay(): Int {
//        val dealerHandStay = deck[0].value + deck[1].value
//        return dealerHandStay
//    }
//    fun playerSumStay(): Int {
//        val playerHand = deck[2].value + deck[3].value
//        return playerHand
//    }
//    fun playerSumHit(): Int {
//        val playerHand = deck[2].value + deck[3].value + deck[5].value
//        return playerHand
//    }
}
