package com.central.scenes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.AppObj
import ktx.actors.*
import ktx.scene2d.*


class StoryScene2 : Group() {
    private val backgroundTex = Texture("room.png")
    private val ripleyTex = Texture("ripley.png")
    private val background = Image(backgroundTex)
    private val ripley = Image(ripleyTex)
    private lateinit var label: Label
    private lateinit var label2: Label

    init {
        AppObj.stg.clear()

        table {
            label = label("We need to escape", "transparent")
        }

        table {
            label2 = label("or they'll tear us apart!", "transparent")
        }

        ripley.setSize(AppObj.stg.width / 2, AppObj.stg.height)
        ripley.setPosition(0f - ripley.width, 0f)
        ripley.alpha = 0f

        label.setPosition(AppObj.stg.width / 2 - label.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        label2.setPosition(AppObj.stg.width / 2 - label2.width / 2, AppObj.stg.height / 2 - label.height / 2)
        label2.alpha = 0f

        background.setSize(AppObj.stg.width, AppObj.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { AppObj.suspense.setVolume(AppObj.suspense.play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    AppObj.stg.clear()
                    this.dispose()
                    AppObj.stg.addActor(StoryScene3())
                }
        )

        ripley += fadeIn(2f)
        ripley += sequence(moveTo(AppObj.stg.width / 2, 0f, 5f), delay(5f), fadeOut(2f))

        label += sequence(delay(2f) + fadeIn(2f)) + delay(2f) + fadeOut(2f)
        label2 += sequence(delay(8f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        this += background
        this += ripley
        this += label
        this += label2
    }

    fun dispose() {
        backgroundTex.dispose()
        ripleyTex.dispose()
        println("memory freed from 'StoryScene2'")
    }
}