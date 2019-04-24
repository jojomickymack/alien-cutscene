package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import com.central.App
import com.central.actors.Alien
import ktx.actors.*
import ktx.scene2d.*


class StoryScene1(app: App) : Group() {
    private val background = Image(ship())
    private lateinit var label: Label
    private lateinit var label2: Label
    private val alien = Alien()
    private val alien2 = Alien()

    init {
        app.stg.clear()

        table {
            label = label("The aliens invaded the ship...", "transparent")
        }

        table {
            label2 = label("and soon began to fight each other.", "transparent")
        }

        val size = 200f

        background.alpha = 0f

        label.setPosition(app.stg.width / 2 - label.width / 2, app.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        label2.setPosition(app.stg.width / 2 - label2.width / 2, app.stg.height / 2 - label.height / 2)
        label2.alpha = 0f

        alien.alpha = 0f
        alien2.alpha = 0f

        background.setSize(app.stg.width, app.stg.height)
        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    app.stg.clear()
                    app.stg.addActor(StoryScene2(app))
                }
        )

        alien.setSize(size, size)
        alien.setPosition(app.stg.width - alien.width, app.stg.height - alien.height)

        alien2.setSize(size, size)
        alien2.setPosition(0f, app.stg.height - alien.height)
        alien2.flip = true

        label += sequence(delay(2f) + fadeIn(2f)) + delay(2f) + fadeOut(2f)
        label2 += sequence(delay(8f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        alien += sequence(fadeIn(3f) +
                Actions.run {
                    alien.currentAnimation = alien.walk
                } + moveTo(0f, 0f, 2f) +
                Actions.run {
                    alien.currentAnimation = alien.stand
                } + delay(2f) +
                Actions.run {
                    alien.flip = true
                    roar().setVolume(roar().play(), 0.2f)
                },
                Actions.run {
                    alien.currentAnimation = alien.fight
                }
        )

        alien2 += sequence(fadeIn(3f) +
                Actions.run {
                    alien2.currentAnimation = alien2.walk
                } + moveTo(app.stg.width - alien2.width, 0f, 2f) +
                Actions.run {
                    alien2.currentAnimation = alien2.stand
                } + delay(2f) +
                Actions.run {
                    alien2.flip = false
                } +
                Actions.run {
                    alien2.currentAnimation = alien2.fight
                }
        )

        this += background
        this += alien
        this += alien2
        this += label
        this += label2
    }
}