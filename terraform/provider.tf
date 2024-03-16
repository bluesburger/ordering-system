terraform {

  required_providers {

    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  backend "s3" {
    bucket = "ordering-system"
    key    = "bluesburguer/application.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  region     = var.defaultRegion
  access_key = var.accessKey
  secret_key = var.secretKey

  default_tags {
    tags = var.tags
  }
}