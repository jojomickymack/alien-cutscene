package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import com.central.assets.Skins.*
import com.central.App
import ktx.actors.*


class StoryScene2(app: App) : Group() {
    private val background = Image(room())
    private val ripley = Image(ripley())
    private var label = Label("We need to escape", my_skin(), "transparent")
    private var label2 = Label("or they'll tear us apart!", my_skin(), "transparent")

    init {
        app.stg.clear()

        ripley.setSize(app.stg.width / 2, app.stg.height)
        ripley.setPosition(0f - ripley.width, 0f)
        ripley.alpha = 0f

        label.setPosition(app.stg.width / 2 - label.width / 2, app.stg.height / 2 - label.height / 2)
        label.alpha = 0f

        label2.setPosition(app.stg.width / 2 - label2.width / 2, app.stg.height / 2 - label.height / 2)
        label2.alpha = 0f

        background.setSize(app.stg.width, app.stg.height)
        background.alpha = 0f

        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } +
                fadeIn(2f) + delay(10f) + fadeOut(2f) +
                Actions.run {
                    app.stg.clear()
                    app.stg.addActor(StoryScene3(app))
                }
        )

        ripley += fadeIn(2f)
        ripley += sequence(moveTo(app.stg.width / 2, 0f, 5f), delay(5f), fadeOut(2f))

        label += sequence(delay(2f) + fadeIn(2f)) + delay(2f) + fadeOut(2f)
        label2 += sequence(delay(8f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        this += background
        this += ripley
        this += label
        this += label2
    }
}