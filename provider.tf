terraform {
  backend "s3" {
    bucket = "ordering-system"
    key    = "bluesburguer/database.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  profile = "default"
  region  = var.regionDefault

  default_tags {
    tags = var.tags
  }
}