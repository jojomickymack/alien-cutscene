package com.central.actors

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import ktx.actors.alpha
import com.central.assets.Images.*

class Alien: Actor() {
    private val tex = alien_sprite()
    private var walkSheet = TextureRegion(tex, 0, 0, tex.width, tex.height)
    private val regions = walkSheet.split(64, 53)[0]

    private var stateTime = 0f
    private val sprite = Sprite(regions[0])

    val stand = Animation(0F, regions[0])
    val walk = Animation(0.15f, regions[0], regions[1], regions[2], regions[3])
    val fight = Animation(0.15f, regions[4], regions[5])
    var currentAnimation = stand

    var flip = false

    init {
        walk.playMode = Animation.PlayMode.LOOP
        fight.playMode = Animation.PlayMode.LOOP
    }

    override fun act(delta: Float) {
        super.act(delta)

        var deltaTime = delta
        if (deltaTime == 0f) return

        if (deltaTime > 0.1f)
            deltaTime = 0.1f

        stateTime += deltaTime

        sprite.setRegion(this.currentAnimation.getKeyFrame(stateTime))
        sprite.setSize(this.width, this.height)
        sprite.setPosition(this.x, this.y)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (flip) sprite.flip(true, false)
        sprite.setAlpha(this.alpha)
        sprite.draw(batch)
    }
}