package com.example.blackjackproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var playerResultView : TextView
    lateinit var playerView1 : ImageView
    lateinit var dealerResultView : TextView
    lateinit var dealerView1: ImageView
    lateinit var dealerScoreView: TextView
    lateinit var cl : ConstraintLayout
    var deck = mutableListOf<Card>()
    var dealerHand = 0
    var playerHand = 0
    var playerScore :Int = 0
    var dealerScore :Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dealerResultView = findViewById(R.id.dealerResultView)
        playerResultView = findViewById(R.id.pResultView)
        val hitButton = findViewById<Button>(R.id.hitButton)
        val stayButton = findViewById<Button>(R.id.stayButton)
        val restartButton = findViewById<Button>(R.id.RestartButton)
        playerView1 = findViewById(R.id.playerView1)
        dealerView1 = findViewById(R.id.dealerView1)
        dealerScoreView = findViewById(R.id.dealerScoreText)



        cl = findViewById(R.id.constraint_Layout)


        createDeck()
        deck.shuffle()
//        backgroundColor()


        hitButton.setOnClickListener {
            checkPlayerCard()

        }
        stayButton.setOnClickListener{
            dealerGenerator()
        }
        restartButton.setOnClickListener{
            val intent = intent
            finish()
            startActivity(intent)
        }

    }


    fun checkPlayerCard(){
        val playerCard = deck.random()
        playerView1.setImageResource(playerCard.image)
        playerHand += playerCard.value
        playerResultView.text = "$playerHand"
        if (playerHand > 21) {
            playerResultView.text = "BUST"
            dealerScoreView.text = "DEALER WIN"
        } else if (playerHand == 21) {
            playerResultView.text = "BLACKJACK!"
            dealerScoreView.text = "PLAYER WIN"

        }
    }
    fun dealerGenerator(){
        GlobalScope.launch(Dispatchers.Main) {
            while (dealerHand <= 16) {
                val dealerCard = deck.random()
                dealerView1.setImageResource(dealerCard.image)
                dealerHand += dealerCard.value
                dealerResultView.text = "$dealerHand"
                delay(500)
                if (dealerHand > 21) {
                    dealerResultView.text = "BUST"
                    dealerScoreView.text = "PLAYER WIN"

                } else if (dealerHand == 21) {
                    dealerResultView.text = "BLACKJACK"
                    dealerScoreView.text = "DEALER WIN"
                }else if (dealerHand >= 17) {
                    if (dealerHand > playerHand) {
                        dealerScoreView.text = "DEALER WIN"
                    }else if (dealerHand == playerHand) {
                        dealerScoreView.text = "DRAW"
                    }else{
                        dealerScoreView.text = "PLAYER WIN"
                    }
                }
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val dealerNumber = dealerScore
//        outState.putInt("savedInt", dealerNumber)
//
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//            val dealerInt = savedInstanceState.getInt("savedInt", 0)
//            dealerScore = dealerInt
//            dealerScoreView.text = dealerScore.toString()
//
//    }


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
//    fun backgroundColor() {
//        cl.setBackgroundResource(R.color.teal_200)
//    }
}