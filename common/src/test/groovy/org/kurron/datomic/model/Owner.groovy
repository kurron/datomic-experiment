package org.kurron.datomic.model

import groovy.transform.Canonical
import org.codehaus.jackson.annotate.JsonProperty

/**
 * Represents a fantasy owner.
 **/
@Canonical
class Owner {
    @JsonProperty( value = ':owner/name' )
    String name = 'unknown'

    @JsonProperty( value = ':owner/teams' )
    List<Team> teams = []
}
