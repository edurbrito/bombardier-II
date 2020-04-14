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

### Initial Scene

#### Objects
There will be a helicopter, placed in the top left corner of the screen, some randomly generated monsters moving towards the helicopter in the top right corner and buildings below them.

### Movement Control and Time Factor

#### Vertical Movement
The vertical movement of the helicopter is controlled not only by the player, but also by a time factor.
- The player can move the helicopter up or down a little bit, using the arrow keys, to escape the monsters that are flying towards him from the right side.
- The altitude of the helicopter decreases by one unit each time it enters the scene from the left side.
- The monsters randomly change their vertical position by one or two units, as they fly towards the helicopter.

#### Horizontal Movement
- The velocity of the helicopter's horizontal movement gives the user time to launch the bombs and shoot the monsters without making it too hard or too easy to finish the game.
- The flying monsters are moving horizontally towards the helicopter, possibly with different moving techniques and speeds.

### Bomb & Missile Launching
- The right arrow key will allow the user to launch a frontal missile.
- Pressing the space key will launch a bomb onto the buildings.
- The number of missiles and bombs should be limited and, therefore, should be updated every time the user presses the space or right arrow key. Also, every time the helicopter comes from the left side, this number should be recalculated.

### Collision Detection
- If the helicopter collides with a skyscraper, the game ends.
- Colliding with monsters weakens the helicopter, reducing its energy.

### Score
The score will be shown on the bottom of the screen. This will be increased every time the helicopter successfully reaches the right side, or when it destroys monsters or buildings.

### Energy
The helicopter has limited energy. This will also be shown on the bottom of the screen and will be reduced every time there is a collision with a monster.

### Game Messages
The player should be presented with a message when starting or ending the game, giving him instructions or showing him the final score.

### Ending
The game ends for three reasons:
- As mentioned before, if the helicopter collides with a building;
- If it loses all the energy, from clashing with monsters;
- Or if every building is successfully put on the ground by its bombs.

### Restarting
It should be possible to restart the game when it ends.