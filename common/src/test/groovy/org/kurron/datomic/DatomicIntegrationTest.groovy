package org.kurron.datomic

import spock.lang.Specification
import datomic.Peer
import datomic.Util

/**
 * Test driver for the Datomic database.
 */
class DatomicIntegrationTest extends Specification {

    def "given_when_then"() {
        given: "concrete Devan"
        String uri = "datomic:free://localhost:4334/seattle"
        Peer.createDatabase(uri)
        def stream = System.classLoader.getResourceAsStream("/seattle-schema.dtm")
        assert stream != null
        Reader schema_rdr = new InputStreamReader(stream)
        schema_tx = Util.readAll(schema_rdr).get(0)

        when: "time is called"


        then:  "time is not null"
    }
}
