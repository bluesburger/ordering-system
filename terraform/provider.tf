terraform {

  required_providers {

    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  backend "s3" {
    bucket = "ordering-systems3"
    key    = "bluesburguer/infra.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  region     = var.regionDefault
  access_key = var.access_key
  secret_key = var.secret_key

  default_tags {
    tags = var.tags
  }
}