package com.example.testapp.game

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.databinding.FragmentGame2Binding


class GameFragment : Fragment(), OnClickHandlerInterface {

    enum class Turn{
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.NOUGHT

    private var crossesScore = 0
    private var noughtsScore = 0

    private lateinit var binding : FragmentGame2Binding

    private var boardList = mutableListOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGame2Binding.inflate(inflater, container, false)
        activity?.setContentView(binding.root)
        initBoard()

        arrayOf(binding.a1, binding.a2, binding.a3,
            binding.b1, binding.b2, binding.b3,
            binding.c1, binding.c2, binding.c3, ).also{array ->
                array.forEach {button ->
                    button.setOnClickListener { view ->
                        boardTapped(view)
                    }
                }
        }

        return inflater.inflate(R.layout.fragment_game2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("AAA", "Game is real")
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    override fun boardTapped(view: View) {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(NOUGHT))
        {
            noughtsScore++
            result("Noughts win!")
        }

        if(checkForVictory(CROSS))
        {
            crossesScore++
            result("Cross win!")
        }

        if(fullBoard()){
            result("Draw")
        }
    }

    private fun checkForVictory(s: String): Boolean {

        //Horizontal Victory
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
            return true

        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true

        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s))
            return true

        //Vertical Victory
        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s))
            return true
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s))
            return true
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s))
            return true

        //Diagonal Victory
        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s))
            return true
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s))
            return true

        return false
    }

    private fun match(button : Button, symbol : String) = button.text == symbol

    private fun result(title: String) {
        val message = "\nNoughts $noughtsScore\n\nCrosses $crossesScore"
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            {
                    _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for(button in boardList)
        {
            button.text = ""
        }
        if(firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button : Button) {

        if(button.text != "")
            return

        if(currentTurn == Turn.NOUGHT){
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }

        else if(currentTurn == Turn.CROSS){
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text = turnText
    }

    companion object{
        const val NOUGHT = "O"
        const val CROSS = "X"
    }


}