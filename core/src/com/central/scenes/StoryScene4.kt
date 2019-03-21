package com.central.scenes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.central.AppObj
import ktx.actors.*


class StoryScene4: Group() {
    private val backgroundTex = Texture("explosion.png")
    private val background = Image(backgroundTex)

    init {
        AppObj.stg.clear()

        background.setSize(AppObj.stg.width, AppObj.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { AppObj.explosion.setVolume(AppObj.explosion.play(), 0.2f) } + fadeIn(2f) + delay(2f) + fadeOut(2f) +
                Actions.run {
                    AppObj.stg.clear()
                    this.dispose()
                    AppObj.stg.addActor(TheEnd())
                }
        )

        this += background
    }

    fun dispose() {
        backgroundTex.dispose()
        println("memory freed from 'StoryScene4'")
    }
}