package com.central.scenes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.central.screens.GameObj

class Scene2 : Scene() {
    val tex = Texture("alien-sprite.png")
    val ship = Image(Texture("ship.png"))
    val alienSnd = Gdx.audio.newSound(Gdx.files.internal("roar.ogg"))

    var walkSheet = TextureRegion(tex, 0, 0, tex.width, tex.height)
    val regions = walkSheet.split(64, 53)[0]
    val stand = Animation(0F, regions[0])
    val walk = Animation(0.15f, regions[0], regions[1], regions[2], regions[3])
    var currentAnimation = walk
    var currentImage = Image(regions[0])
    var actor = Actor()
    var stateTime = 0f
    val sprite = Sprite(regions[0])
    var flip = false

    init {
        GameObj.stg.clear()

        this.actor.color.a = 0f
        this.ship.color.a = 0f

        this.walk.playMode = Animation.PlayMode.LOOP

        actor.setSize(400f, 400f)
        actor.setPosition(GameObj.stg.width / 2, GameObj.stg.height / 2)

        actor.addAction(Actions.sequence(
                Actions.fadeIn(2f),
                Actions.moveTo(0f, 0f, 2f),
                Actions.run {
                    this.currentAnimation = stand
                }, Actions.delay(2f),
                Actions.run {
                    this.flip = true
                    alienSnd.play()
                }
        ))

        ship.color.a = 0f
        ship.setSize(GameObj.stg.width, GameObj.stg.height)
        ship.addAction(Actions.fadeIn(2f))
    }

    override fun act(delta: Float) {
        var deltaTime = delta
        if (deltaTime == 0f) return

        if (deltaTime > 0.1f)
            deltaTime = 0.1f

        this.stateTime += deltaTime

        this.ship.act(delta)
        this.actor.act(delta)

        this.sprite.setRegion(this.currentAnimation.getKeyFrame(stateTime))
        this.sprite.setSize(this.actor.width, this.actor.height)
        this.sprite.setPosition(this.actor.x, this.actor.y)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        ship.draw(batch, parentAlpha)
        if (flip) this.sprite.flip(true, false)
        this.sprite.color.a = this.actor.color.a
        sprite.draw(batch)
    }
}