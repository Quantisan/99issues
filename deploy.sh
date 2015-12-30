#!/bin/sh

lein clean && \
lein cljsbuild once min && \
gh-pages -d resources/public

