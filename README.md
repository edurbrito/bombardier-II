<link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@700&display=swap" rel="stylesheet">

###### LPOO-2020-G72

# Bombardier II : The Revenge of The Skyscrapers

> A suicide helicopter tries to bomb a city taken by the forces of evil, while escaping from monsters moving in its direction.

```java

                                             \-O
                                                                      <-/-{
                                             _
                                             |
               <-(-(                         W
      T                 T                                               T
      # S                #                                           S  #
      # #                #                       R   R  R     R      #  #
      # #                #                       #   #  #    Q#    Q #  #    Q
      # #           P    #                R#     #   #  #    ##    # #  #    #
      # #           #    #   O            ##     #   #  #    ##    # #  #    #
      # #         N #   N#   #        N   ##   N #   #  #    ##    # #  #    # N
      # #         # #   ##   # M      #   ##   # #   #  #    ##    # # M#    # #
 L    # #         # # L ##   # #   L  #   ##   # #   #  # L  ##L   # # ##    # #
 #    # #   K     # # # ##   # #   #  #   ##   # #   #  # #  ###   # # ## K  # #
 # J  # #   #     #J# # ## J # #   #J #J  ##   # #   #  # #  ###   # # ## #  # #
 # #  # #   #     ###I# ## # # #   ## ##  ##   # #   #  # #  ###   # #I## #  # #
 # #  # #   #     ##### ## # # #H  ## ##  ##   # #   #  # #  ###   # #### #  # #
G# #  # #   #   G ##### ## # # ##G ## ##G ##   # #   #  #G#  ###   # #### #  # #
## #  # # F #   #F##### ## #F# ### ## ### ##F  # #  F#  ###  ###   # ####F#FF# #
## #  # # # #  E####### ## ### ### ## ### ###  #E#E ##  ###  ###  E#E######### #
## # D# #D#D#  ######## ## ### ### ## ### ### D#### ##  ###D ###  ############ #
## # ## #####C ######## ## ###C###C## ### ### ##### ##  #### ###C ############ #
##B#B## ######B######## ## ##########B### ### #####B##B #### ####B############ #
####### ###############A##A############## ### ######### #### ################# #
==========Blocks: 0700/0700, City:          OPorto (01), Score:      -1=========
```

## Description

*This is a more elaborate version of the [Bombardier](https://www.uvlist.net/game-187836-bombardier) game for Linux.*

### There will be a helicopter, skyscrapers and some flying monsters!

The helicopter, starting from the top left corner of the scene, will have to destroy the buildings bellow. It may launch some bombs to avoid colliding with them, while losing altitude. At the same time, the monsters will be moving towards it, from the right side. The helicopter will have to launch frontal missiles to defeat them, being able to boost up or down, a little bit, to escape or face the monsters.

## Main Features

### Movement Controls and Time Factor
#### Vertical Movement
*The vertical movement of the helicopter is controlled both by the player and by a time factor*
- The player can move the helicopter up and down  a little bit by using up/down arrow keys to escape the flying monsters that move towards him from the right side.
- The altitude of the helicopter is decreased by one each time the helicopter enters the scene from the left side.
- The Flying monsters can also move vertically by one/two units. That movement can vary randomly as they move towards the helicopter.
#### Horizontal Movement
 - The velocity of the helicopter's horizontal movement gives the user time to launch the bombs and shoot the monsters without making it to hard or to easy to win the game.
- The flying monsters will move towards the helicopter possibly with different moving techniques.
### Bomb & Missile Launching
- The right arrow key will allow the user to launch a frontal missile
- Pressing the Space key will launch a bomb 
- The number of missiles and bombs should be limited and, therefore, should be refreshed every time the user presses the space or right arrow key. Also, every time the helicopter comes from the right side the number of missiles and bombs should be recalculated.
### Collision Detection
- If the helicopter collides with a skyscrapper the game ends.
- Colliding with monsters decreases the energy/life of the player.
###  Player's Score
 The player’s score, which will show ont the bottom of the screen, will increase every time it successfully reaches the right side of the screen and also when it destroys the monsters or the buildings
### Energy/Lifes
 The Player has limited energy that decreases on every collision with a monster. The energy should be shown on the bottom of the screen.
### Start/End of Game Messages
The Player should be presented with a message before starting the game and when the game ends - to congratulate the winner if he won or a game over message if he lost. Also, the score should be shown in the game screen. 
### Restart Game
It should be possible to restart the game when it ends.