package org.apache.maven.surefire.its;


import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;
import java.util.ArrayList;

/**
 * Test library using a conflicting version of plexus-utils
 *
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 *
 */
public class PlexusConflictTest
    extends TestCase
{
    public void testPlexusConflict ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/plexus-conflict" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        verifier.executeGoal( "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }

    public void testPlexusConflictIsolatedClassLoader ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/plexus-conflict" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        ArrayList goals = new ArrayList();
        goals.add( "test" );
        goals.add( "-Dsurefire.useSystemClassLoader=false" );
        verifier.executeGoals( goals );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }
}