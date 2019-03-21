package com.central.screens

import com.badlogic.gdx.Gdx
import com.central.App
import com.central.AppObj
import com.central.scenes.StoryScene1
import com.central.scenes.StoryScene2
import com.central.scenes.TitleScene
import ktx.actors.plusAssign
import ktx.app.KtxScreen

class Game(val application: App) : KtxScreen {

    val music = Gdx.audio.newMusic(Gdx.files.internal("theme.ogg"))

    init {
        AppObj.stg += TitleScene()

        // you can quickly skip to whichever scene you're working on
        //AppObj.stg += StoryScene2()
        music.volume = 0.5f
        music.play()
    }

    override fun render(delta: Float) {
        AppObj.stg.act(delta)
        AppObj.stg.draw()
    }

    override fun dispose() {
        music.dispose()
        println("all disposable memory freed in Game")
        super.dispose()
    }
}
