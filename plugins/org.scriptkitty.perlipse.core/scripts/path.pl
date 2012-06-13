use strict;

use constant {
    CYGPATH => '/usr/bin/cygpath'
};

my $isCygwin = ($^O eq 'cygwin') ? 1 : 0;

if ($isCygwin && !-f CYGPATH)
{
    die sprintf 'unable to find cygpath at location: [%s]', CYGPATH;
} 

my @paths = ();
foreach my $path (@INC)
{
    #
    # convert cygwin paths - even though cygwin's perl is executed using a 
    # windows style path, a location of cygpath can be represented as a unix
    # path, eliminating the need for us to know the location cygwin was
    # installed in
    #
    if ($isCygwin)
    {
        my $cmd = CYGPATH;
        # convert to a windows style path
        $path = `$cmd -w $path`;
        chomp $path;
    }
    
    push @paths, $path;    
}

print join(' ', @paths) . "\n";
