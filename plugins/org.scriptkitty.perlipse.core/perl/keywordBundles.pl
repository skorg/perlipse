#
# generates resource bundles containing the perl keywords. each type of keyword
# is stored in a separate bundle so they can be easily categorized (and so
# they fit into the bundle name/value pair model).
#
use strict;
 
use B::Keywords qw();

use File::Spec qw();

use IO::File;

use constant {
    SUFFIX => 'properties',
};

my @PERLDOC_FILTERS = (
    qr/^__/,
    qr/^\-/,
);

#
# TODO: provide documentation where applicable
#
# perlvar will have defintions for scalars
#

my @TO_GENERATE = (
    {
        keywords      => sub { return @B::Keywords::Scalars; },
        filename      => 'scalarKeywords',
        documentation => sub { return _genEmptyDoc(@_) },
    },
    {
        keywords => sub { return @B::Keywords::Arrays; },
        filename => 'arrayKeywords',
        documentation => sub { return _genEmptyDoc(@_) },
    },
    {
        keywords => sub { return @B::Keywords::Hashes; },
        filename => 'hashKeywords',
        documentation => sub { return _genEmptyDoc(@_) },
    },
    {
        keywords => sub { return @B::Keywords::Filehandles; },
        filename => 'fileHandleKeywords',
        documentation => sub { return _genEmptyDoc(@_) },
    },
    {
        keywords => sub { return @B::Keywords::Functions; },
        filename => 'functionKeywords',
        documentation => sub { return _genPerlDoc(@_) },
    },
    {
        keywords => sub { return @B::Keywords::Barewords; },
        filename => 'barewordKeywords',
        documentation => sub { return _genEmptyDoc(@_) },
    },
);

## main

my $dir = $ARGV[0];
_validateDir($dir);

foreach my $generate (@TO_GENERATE)
{
    my $file = _getOutputFileHandle($dir, $generate->{filename});
    my @keywords = $generate->{keywords}->(); 

    foreach my $keyword (@keywords)
    {
        $keyword = _escapeKeyword($keyword);
        my $docs = $generate->{documentation}->($keyword);
#        print STDERR sprintf "keyword: %s\ndocs: %s\n--\n", $keyword, $docs;
        printf $file "%s=%s\n", $keyword, $docs;
    }
    $file->close;
}

## private

sub _escapeKeyword
{
    my ($keyword) = @_;

    # change $= -> $\=    
    $keyword =~ s/(.*?)\=/$1\\=/;
    # change $: -> $\:        
    $keyword =~ s/(.*?)\:/$1\\:/;
    
    return $keyword;   
}

sub _genEmptyDoc
{
    return '';
}

sub _genPerlDoc
{
    my ($keyword) = @_;

    if (_matches_filter($keyword, \@PERLDOC_FILTERS))
    {
#        printf STDERR "skipping keyword [%s], matched filter\n", $keyword;
        return '';
    }
    
    #
    # TODO: replace w/ Pod::Find, Pod::POM calls?
    # 
    my $perldoc = `perldoc -t -f $keyword`;
    
    #
    # take the first block of text before the double newline for display in
    # the popup annotations
    #
    $perldoc =~ s/(.*?\.)\n\n.*/$1/sm;
    
    # replace slashes and newlines for java
    $perldoc =~ s/\\/\\\\/g;
    $perldoc =~ s/\n/\\n/g;
    
    return ($perldoc) ? $perldoc : '';
}

sub _getOutputFileHandle
{
    my $file = sprintf '%s.%s', File::Spec->catfile(@_), SUFFIX;
    printf STDERR "creating bundle: %s\n", $file; 
    
    return IO::File->new($file, 'w');     
}

sub _matches_filter
{
    my ($keyword, $filters) = @_;

    return grep {$keyword =~ m/$_/} @{$filters};
}

sub _validateDir
{
    my ($dir) = @_;
    
    if (!$dir)
    {
        printf "usage: %s <path_to_bundle_location>\n", $0;
        exit(1);
    }

    if (!(-e $dir && -d $dir))
    {
        printf "invalid output directory: %s\n", $dir;
        exit(1); 
    }    
}


