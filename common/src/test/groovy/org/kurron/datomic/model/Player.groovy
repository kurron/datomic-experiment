package org.kurron.datomic.model

import groovy.transform.Canonical
import org.codehaus.jackson.annotate.JsonProperty

/**
 * Represents a fantasy player.
 **/
@Canonical
class Player {

    @JsonProperty( value = ':player/name' )
    String name = 'unknown'

    @JsonProperty( value = ':player/games' )
    List<Game> games = []
}
