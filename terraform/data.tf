data "aws_iam_access_keys" "terraform" {
  user = "github"
}

data "github_actions_environment_secrets" "aws_access_key_id" {
    name        = "ordering-system"
    environment = "AWS_ACCESS_KEY_ID"
}

data "github_actions_environment_secrets" "aws_secret_access_key" {
    name        = "ordering-system"
    environment = "AWS_SECRET_ACCESS_KEY"
}