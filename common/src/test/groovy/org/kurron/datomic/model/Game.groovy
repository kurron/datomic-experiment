package org.kurron.datomic.model

import groovy.transform.Canonical
import org.codehaus.jackson.annotate.JsonProperty

/**
 * Represents a fantasy player's real NFL game statistics.
 **/
@Canonical
class Game {
    @JsonProperty( value = ':game/week' )
    int week

    @JsonProperty( value = ':game/rushing-yardage' )
    int rushingYardage

    @JsonProperty( value = ':game/passing-yardage' )
    int passingYardage
}
