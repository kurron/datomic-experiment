package org.kurron.datomic.model

import org.codehaus.jackson.map.DeserializationConfig
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.SerializationConfig
import spock.lang.Specification

/**
 * A learning test just to see how the JSON structures might look.
 */
class JsonSerializerLearningTest extends Specification {
    def 'dump JSON structures'()
    {
        given: 'a JSON serializer'
        ObjectMapper mapper = new ObjectMapper()
        mapper.configure( SerializationConfig.Feature.INDENT_OUTPUT, true )
        mapper.configure( SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false )
        mapper.configure( SerializationConfig.Feature.USE_ANNOTATIONS, true )
        mapper.configure( SerializationConfig.Feature.AUTO_DETECT_FIELDS, false )
        mapper.configure( SerializationConfig.Feature.AUTO_DETECT_GETTERS, false )
        mapper.configure( SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false )

        when: 'an object is serialized into JSON'
        Owner sut = new Owner()
        Team team = new Team()
        10.times {
            Player player = new Player()
            16.times {
                player.games.add( new Game() )
            }
            team.players.add( player )
        }
        sut.teams.add( team )
        String json = mapper.writer().writeValueAsString( sut )

        then: 'printout JSON'
        println sut.class.simpleName
        println "$json"
        true
    }
}