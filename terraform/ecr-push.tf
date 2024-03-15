# Definição de um recurso de execução local para fazer o push da imagem
resource "null_resource" "push_image_to_ecr" {
  provisioner "local-exec" {
    command = "aws ecr get-login-password --region ${var.aws_region} | docker login --username AWS --password-stdin ${var.aws_account_id}.dkr.ecr.${var.aws_region}.amazonaws.com && docker build -t ${var.aws_account_id}.dkr.ecr.${var.aws_region}.amazonaws.com/ordering-systems3:latest . && docker push ${var.aws_account_id}.dkr.ecr.${var.aws_region}.amazonaws.com/ordering-systems3:latest"
  }
}
