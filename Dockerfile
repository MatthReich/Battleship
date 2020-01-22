FROM openjdk:11

# works on linux while building
# ARG SBT_VERSION=1.3.7

# works on windows while building
ARG SBT_VERSION=2.12.10


# Install sbt

RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install -y sbt libxrender1 libxtst6 libxi6

WORKDIR /battleship
ADD . /battleship
CMD sbt run