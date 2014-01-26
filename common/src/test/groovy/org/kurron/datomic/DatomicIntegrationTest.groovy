package org.kurron.datomic

import spock.lang.Specification
import datomic.Peer
import datomic.Util
import datomic.Connection

/**
 * Test driver for the Datomic database.
 */
class DatomicIntegrationTest extends Specification
{

    //final String uri = "datomic:free://localhost:4334/seattle"
    final String uri = 'datomic:mem://seattle'

    def 'community name query'()
    {
        given: 'populated database'
        Connection connection = openConnection()
        applySchema( connection )
        seedDatabase( connection )

        when: 'a query for all entities that have the attribute community/name is run'
        // find all variables for community where any entity has the community/name attribute
        def results = Peer.q( '[:find ?c :where [?c :community/name]]', connection.db() )

        then: '150 instances are found'
        results
        150 == results.size()

        // iterate over the results
        results.each {
            def id = it.get( 0 )
            def entity = connection.db().entity( id )
            def name = entity.get( ':community/name' )
            def neighborhood = entity.get( ':community/neighborhood' )
            def neighborhoodName = neighborhood.get( ':neighborhood/name' )
            println "${id} The community of ${name} is in the neighborhood of ${neighborhoodName}"
        }
    }

    private void seedDatabase( Connection connection )
    {
        def results = connection.transact( loadTransaction( 'seattle-data.dtm' ) ).get()
        assert results
    }

    private List loadTransaction( String resource )
    {
        List seedTransaction = Util.readAll( loadResource( resource ) ).first() as List
        assert seedTransaction
        seedTransaction
    }

    private Reader loadResource( String resource )
    {
        def seedStream = getClass().getClassLoader().getResourceAsStream( resource )
        assert seedStream
        def seedReader = new InputStreamReader( seedStream )
        seedReader
    }

    private void applySchema( Connection connection )
    {
        def schemaInsertResults = connection.transact( loadTransaction( 'seattle-schema.dtm' ) ).get()
        assert schemaInsertResults
    }

    private Connection openConnection( )
    {
        def created = Peer.createDatabase( uri )
        assert created
        def connection = Peer.connect( uri )
        assert connection
        connection
    }
}
