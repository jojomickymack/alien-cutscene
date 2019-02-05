package com.central.hudactors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.screens.GameObj
import com.badlogic.gdx.scenes.scene2d.actions.Actions.run


fun TextWindow.trigger() {
    this.triggered = true
    GameObj.hudStg.addActor(dialog)
}

class TextWindow(text: String) {
    var dialog = object : Dialog("", GameObj.skin, "default") {
        override fun result(obj: Any) {
            println("result $obj")
        }
    }

    fun remove() {
        dialog.addAction(sequence(Actions.fadeOut(this.fadeDuration), run(Runnable {
            GameObj.paused = false
            GameObj.textbox = false
            this.dialog.remove()
        })))
    }

    private val fadeDuration = 0.5f
    private var label = Label(text, GameObj.skin, "default")
    var triggered = false

    init {
        this.label.setWrap(true)
        this.label.setAlignment(0)

        this.dialog.contentTable.add(label).width(400f)
        this.dialog.width = 500f
        this.dialog.height = 300f

        this.dialog.addListener(object : InputListener() {
            override fun keyDown(event: InputEvent, keycode: Int): Boolean {
                remove()
                return true
            }
        })

        this.dialog.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                remove()
                return true
            }
        })

        this.dialog.color.a = 0f
        this.dialog.addAction(sequence(Actions.fadeIn(this.fadeDuration), run(Runnable {
            GameObj.paused = true
            GameObj.textbox = true
        })))

        this.dialog.setPosition(GameObj.hudStg.width / 2 - this.dialog.width / 2, GameObj.hudStg.height / 2 - this.dialog.height / 2)
    }
}