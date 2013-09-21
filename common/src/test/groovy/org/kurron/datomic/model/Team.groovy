package org.kurron.datomic.model

import groovy.transform.Canonical
import org.codehaus.jackson.annotate.JsonProperty

/**
 * Represents a fantasy team.
 **/
@Canonical
class Team {
    @JsonProperty( value = ':team/name' )
    String name = 'unknown'

    @JsonProperty( value = ':team/players' )
    List<Player> players = []
}
