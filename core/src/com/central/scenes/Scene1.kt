package com.central.scenes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.central.screens.GameObj

class Scene1 : Scene() {
    val logo = Image(Texture("alien-logo.png"))
    val egg = Image(Texture("alien-egg.png"))

    init {
        logo.color.a = 0f
        //logo.setSize((logo.width * 0.75).toFloat(), logo.height)
        logo.setPosition(GameObj.stg.width / 2 - logo.width / 2, GameObj.height - logo.height)
        egg.color.a = 0f
        egg.setSize((egg.width * 0.75).toFloat(), (egg.height * 0.75).toFloat())
        egg.setPosition(GameObj.stg.width / 2 - egg.width / 2, 0f)

        logo.addAction(sequence(
                Actions.fadeIn(2f),
                Actions.run {
                    egg.addAction(Actions.fadeIn(2f))
                },
                Actions.delay(5f),
                Actions.run {
                    logo.addAction(Actions.fadeOut(2f))
                    egg.addAction(Actions.fadeOut(2f))
                },
                Actions.delay(2f),
                Actions.run {
                    GameObj.stg.clear()
                    GameObj.stg.addActor(Scene2())
                }
        ))

        this.addActor(egg)
        this.addActor(logo)
    }

    override fun act(delta: Float) {
        this.children.forEach {
            it.act(delta)
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        this.children.forEach {
            it.draw(batch, parentAlpha)
        }
    }
}